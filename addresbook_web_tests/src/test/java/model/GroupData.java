package model;

public record GroupData(String name, String header, String footer) {

    public GroupData(){
        this ("","","");
    }

    public GroupData withName(String Name) {
        return new GroupData(Name,this.header,this.footer);
    }

    public GroupData withHeader(String Header) {
        return new GroupData(this.name,Header,this.footer);
    }

    public GroupData withFooter(String Footer) {
        return new GroupData(this.name,this.header,Footer);
    }
}