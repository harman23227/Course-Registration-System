class complaint {
    private static int complaintcount=0;
    private int id;
    private String description;
    private String status;

    public complaint(String description) {
        this.id=++complaintcount;
        this.description=description;
        this.status="pending";
    }

    public int getid() {
        return id;
    }

    public String getdescription() {
        return description;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status=status;
    }
}
