package eu.skillcraft.exercises.qdoc.preparation;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class QDocId implements Serializable {

	private final UUID id;

	public QDocId(UUID id) {
		this.id = id;
	}

	public UUID getValue() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QDocId docNumber = (QDocId) o;
		return Objects.equals(id, docNumber.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return id.toString();
	}

	public static QDocId random() {
		return new QDocId(UUID.randomUUID());
	}
}
