package eu.skillcraft.exercises.qdoc.preparation;

import lombok.Value;

@Value
public class VersionCandidateId {
    int revision;

    public VersionCandidateId(int revision) {
        if(revision <= 0) {
            throw new RuntimeException("revision needs to be positive number");
        }
        this.revision = revision;
    }

    public int getRevision() {
        return revision;
    }
}
