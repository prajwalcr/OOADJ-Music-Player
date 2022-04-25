package com.example.musicplayer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Podcast {
    private StringProperty id;
    private StringProperty speakerName;
    private StringProperty name;
    private StringProperty length;
    private StringProperty duration;
    private StringProperty album;
    private StringProperty url;
    private Image image;

    public Podcast() {}

    public Podcast(String url) {
        this.url = new SimpleStringProperty(url);
    }

    public Podcast(String id, String speakerName, String name, String length, String duration, String album, String url) {
        this.id = new SimpleStringProperty(id);
        this.speakerName = new SimpleStringProperty(speakerName);
        this.name = new SimpleStringProperty(name);
        this.length = new SimpleStringProperty(length);
        this.duration = new SimpleStringProperty(duration);
        this.album = new SimpleStringProperty(album);
        this.url = new SimpleStringProperty(url);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getSpeakerName() {
        return speakerName.get();
    }

    public void setSpeakerName(String SpeakerName) {
        this.speakerName.set(SpeakerName);
    }

    public StringProperty speakerNameProperty() {
        return speakerName;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDuration() {
        return length.get();
    }

    public void setDuration(String duration) {
        this.length.set(duration);
    }

    public StringProperty durationProperty() {
        return length;
    }

    public String getRate() {
        return duration.get();
    }

    public StringProperty rateProperty() {
        return duration;
    }

    public void setRate(String rate) {
        this.duration.set(rate);
    }

    public String getFormat() {
        return album.get();
    }

    public StringProperty formatProperty() {
        return album;
    }

    public void setFormat(String format) {
        this.album.set(format);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
