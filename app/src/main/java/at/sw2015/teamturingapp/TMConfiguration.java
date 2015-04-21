package at.sw2015.teamturingapp;


import java.util.Vector;

public class TMConfiguration {

    private String author_ = "";
    private int tape_count_ = 0;
    private String initial_state_;
    private String current_state_;
    private Vector<Integer> head_positions_;
    private Vector<String> all_tapes_;
    private Vector<Vector<String>> all_rules_;

    public TMConfiguration(String author, int tape_count, String initial_state,
                           Vector<Integer> head_positions, Vector<String> all_tapes,
                           Vector<Vector<String>> all_rules) {
        this.author_ = author;
        this.tape_count_ = tape_count;
        this.initial_state_ = current_state_ = initial_state;
        this.head_positions_ = head_positions;
        this.all_tapes_ = all_tapes;
        this.all_rules_ = all_rules;
    }

    public String getAuthor() {
        return author_;
    }

    public void setAuthor(String author_) {
        this.author_ = author_;
    }

    public int getTapeCount() {
        return tape_count_;
    }

    public void setTapeCount(int tape_count_) {
        this.tape_count_ = tape_count_;
    }

    public Vector<Integer> getHeadPositions() {
        return head_positions_;
    }

    public void setHeadPositions(Vector<Integer> head_positions_) {
        this.head_positions_ = head_positions_;
    }

    public Vector<String> getAllTapes() {
        return all_tapes_;
    }

    public void setAllTapes(Vector<String> all_tapes_) {
        this.all_tapes_ = all_tapes_;
    }

    public Vector<Vector<String>> getAllRules() {
        return all_rules_;
    }

    public void setAllRules(Vector<Vector<String>> all_rules_) {
        this.all_rules_ = all_rules_;
    }

    public String getInitialState() {
        return initial_state_;
    }

    public void setInitialState(String initial_state) {
        this.initial_state_ = initial_state;
    }

}
