package com.risusapp.anarcho.capitalism.quotes;

public class Quote {
    String name;
    String img;
    String sound_resource;
    String description;

    public Quote() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSound_resource() {
        return sound_resource;
    }

    public void setSound_resource(String sound_resource) {
        this.sound_resource = sound_resource;
    }

    public Quote(String name, String img, String sound_resource, String description) {
        this.name = name;
        this.img = img;
        this.sound_resource = sound_resource;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", sound_resource='" + sound_resource + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
