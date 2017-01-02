package de.hska.workplacemanagement.domain;

public class WorkplaceNode {

    private Workplace workplace;
    private Workplace follower;

    public WorkplaceNode(Workplace workplace, Workplace follower) {
        this.workplace = workplace;
        this.follower = follower;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Workplace getFollower() {
        return follower;
    }

    public void setFollower(Workplace follower) {
        this.follower = follower;
    }
}
