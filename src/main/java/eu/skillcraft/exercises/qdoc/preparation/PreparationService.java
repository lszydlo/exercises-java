package eu.skillcraft.exercises.qdoc.preparation;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;


public class PreparationService {

	private final QDocDraftRepo repo;
	private final NumberFactory generator;


	public PreparationService(QDocDraftRepo repo, NumberFactory generator) {
		this.repo = repo;
		this.generator = generator;
	}

	public void handle(DoCreateQDocDraft command) {
		QDocNumber number = generator.create();
		repo.save(new QDocDraft(number, command.title, command.docType));
	}


	static class QDocDraft {

		private List<Patch<String>> patches;
		private List<VersionCandidateId> vcIds;
		private QDocNumber number;
		private final String title;
		private final QDocType docType;

		public QDocDraft(QDocNumber number, String title, QDocType docType) {
			this.number = checkNotNull(number);
			this.patches = new LinkedList<>();
			this.vcIds = new ArrayList<>();
			this.title = title;
			this.docType = docType;
		}

		public void updateContent(String newContent) {
			List<String> currentContent = contentLines();
			List<String> updatedContent = Arrays.asList(newContent.split("\\n"));

			try {
				Patch<String> patch = DiffUtils.diff(currentContent, updatedContent);
				patches.add(patch);
			} catch (com.github.difflib.algorithm.DiffException e) {
				throw new RuntimeException(e);
			}
		}

		private List<String> contentLines() {
			List<String> content = List.of("");

			for (Patch<String> patch : patches) {
				try {
					content = patch.applyTo(content);
				} catch (PatchFailedException e) {
					throw new RuntimeException(e);
				}
			}
			return content;
		}

		public VersionCandidateId createVC() {
			VersionCandidateId id = new VersionCandidateId(patches.size());
			vcIds.add(id);
			return id;
		}

		void markVcAsCompleted(VersionCandidateId vcId) {

		}
	}
}
