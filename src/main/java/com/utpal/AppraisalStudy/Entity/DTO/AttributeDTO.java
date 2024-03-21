package com.utpal.AppraisalStudy.Entity.DTO;

public class AttributeDTO {
    private long id;
    private long techinal;
    private long nonTechnical;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTechinal() {
        return techinal;
    }

    public void setTechinal(long techinal) {
        this.techinal = techinal;
    }

    public long getNonTechnical() {
        return nonTechnical;
    }

    public void setNonTechnical(long nonTechnical) {
        this.nonTechnical = nonTechnical;
    }
}
