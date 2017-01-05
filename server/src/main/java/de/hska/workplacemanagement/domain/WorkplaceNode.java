package de.hska.workplacemanagement.domain;

public class WorkplaceNode {

    private Workplace workplace;
    private WorkplaceNode follower;

    public WorkplaceNode(Workplace workplace, WorkplaceNode follower) {
        this.workplace = workplace;
        this.follower = follower;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public WorkplaceNode getFollower() {
        return follower;
    }

    public void setFollower(WorkplaceNode follower) {
        this.follower = follower;
    }
}
