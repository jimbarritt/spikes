package com.jimbarritt.spikes.metricsexample.bee;

public class NaughtyBee extends Bee {

    public String thisClassHasSomeNaughtDuplication() {
        StringBuilder sb = new StringBuilder();

        sb.append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code");

        return sb.toString();
    }

    public String thisClassHasSomeNaughtDuplication2() {
        StringBuilder sb = new StringBuilder();

        sb.append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code")
          .append("I am going to duplicate more than six lines of code");


        return sb.toString();
    }
}