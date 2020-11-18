package eu.skillcraft.exercises.qdoc.preparation;

import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PreparationService {

	private final QDocDraftRepo repo;
	private final QDocNumberFactory numberFactory;

	public PreparationService(QDocDraftRepo repo, QDocNumberFactory numberFactory) {
		this.repo = repo;
		this.numberFactory = numberFactory;
	}

	public void handle(DoCreateQDocDraft command) {

		QDocNumberFactory.QDocNumber number = numberFactory.create();
		repo.save(new QDocDraft(number,command.title,command.type));
	}

	static class QDocDraft {

		private List<Patch<String>> patches;
		private List<VersionCandidateId> vcIds;
		private QDocNumberFactory.QDocNumber number;
		private  String title;
		private  String type;

		public QDocDraft(QDocNumberFactory.QDocNumber number,  String title, String type) {
			this.number = checkNotNull(number);
			this.patches = new LinkedList<>();
			this.vcIds = new ArrayList<>();
			this.title = title;
			this.type = type;
		}

		public void updateContent(String newContent) {
			List<String> currentContent = contentLines();
			List<String> updatedContent = Arrays.asList(newContent.split("\\n"));

			try {
				Patch<String> patch = DiffUtils.diff(currentContent, updatedContent);
				patches.add(patch);
			} catch (DiffException e) {
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
	}

}
