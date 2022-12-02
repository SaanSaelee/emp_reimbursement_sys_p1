package com.revature.models;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

public class Reimbursement {

    private int reimb_id;
    private int reimb_amount;
    private Timestamp reimb_submitted;
    private Timestamp reimb_resolved;
    private String reimb_description;
    private byte[] reimb_receipt;
    private int reimb_author;
    private int reimb_resolver;
    private int reimb_status_id;
    private String reimb_status_type;
    private int reimb_type_id;

    //constructors
    public Reimbursement() {
        super();
    }

    public Reimbursement(int reimb_id, int reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
                         String reimb_description, byte[] reimb_receipt, int reimb_author, int reimb_resolver,
                         int reimb_status_id, String reimb_status_type, int reimb_type_id) {
        this.reimb_id = reimb_id;
        this.reimb_amount = reimb_amount;
        this.reimb_submitted = reimb_submitted;
        this.reimb_resolved = reimb_resolved;
        this.reimb_description = reimb_description;
        this.reimb_receipt = reimb_receipt;
        this.reimb_author = reimb_author;
        this.reimb_resolver = reimb_resolver;
        this.reimb_status_id = reimb_status_id;
        this.reimb_status_type = reimb_status_type;
        this.reimb_type_id = reimb_type_id;
    }

    public Reimbursement(int reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved, String reimb_description, byte[] reimb_receipt, int reimb_author, int reimb_resolver, int reimb_status_id, String reimb_status_type, int reimb_type_id) {
        this.reimb_amount = reimb_amount;
        this.reimb_submitted = reimb_submitted;
        this.reimb_resolved = reimb_resolved;
        this.reimb_description = reimb_description;
        this.reimb_receipt = reimb_receipt;
        this.reimb_author = reimb_author;
        this.reimb_resolver = reimb_resolver;
        this.reimb_status_id = reimb_status_id;
        this.reimb_status_type = reimb_status_type;
        this.reimb_type_id = reimb_type_id;
    }

    //getters and setters

    public int getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(int reimb_id) {
        this.reimb_id = reimb_id;
    }

    public int getReimb_amount() {
        return reimb_amount;
    }

    public void setReimb_amount(int reimb_amount) {
        this.reimb_amount = reimb_amount;
    }

    public Timestamp getReimb_submitted(Instant now) {
        return reimb_submitted;
    }

    public void setReimb_submitted(Timestamp reimb_submitted) {
        this.reimb_submitted = reimb_submitted;
    }

    public Timestamp getReimb_resolved() {
        return reimb_resolved;
    }

    public void setReimb_resolved(Timestamp reimb_resolved) {
        this.reimb_resolved = reimb_resolved;
    }

    public String getReimb_description() {
        return reimb_description;
    }

    public void setReimb_description(String reimb_description) {
        this.reimb_description = reimb_description;
    }

    public byte[] getReimb_receipt() {
        return reimb_receipt;
    }

    public void setReimb_receipt(byte[] reimb_receipt) {
        this.reimb_receipt = reimb_receipt;
    }

    public int getReimb_author() {
        return reimb_author;
    }

    public void setReimb_author(int reimb_author) {
        this.reimb_author = reimb_author;
    }

    public int getReimb_resolver() {
        return reimb_resolver;
    }

    public void setReimb_resolver(int reimb_resolver) {
        this.reimb_resolver = reimb_resolver;
    }

    public int getReimb_status_id() {
        return reimb_status_id;
    }

    public void setReimb_status_id(int reimb_status_id) {
        this.reimb_status_id = reimb_status_id;
    }

    public int getReimb_type_id() {
        return reimb_type_id;
    }

    public void setReimb_type_id(int reimb_type_id) {
        this.reimb_type_id = reimb_type_id;
    }

    public String getReimb_status_type() {
        return reimb_status_type;
    }

    public void setReimb_status_type(String reimb_status_type) {
        this.reimb_status_type = reimb_status_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimb_id == that.reimb_id && reimb_amount == that.reimb_amount && reimb_author == that.reimb_author && reimb_resolver == that.reimb_resolver && reimb_status_id == that.reimb_status_id && reimb_type_id == that.reimb_type_id && Objects.equals(reimb_submitted, that.reimb_submitted) && Objects.equals(reimb_resolved, that.reimb_resolved) && Objects.equals(reimb_description, that.reimb_description) && Arrays.equals(reimb_receipt, that.reimb_receipt) && Objects.equals(reimb_status_type, that.reimb_status_type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_status_type, reimb_type_id);
        result = 31 * result + Arrays.hashCode(reimb_receipt);
        return result;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_submitted=" + reimb_submitted +
                ", reimb_resolved=" + reimb_resolved +
                ", reimb_description='" + reimb_description + '\'' +
                ", reimb_receipt='" + reimb_receipt + '\'' +
                ", reimb_author=" + reimb_author +
                ", reimb_resolver=" + reimb_resolver +
                ", reimb_status_id=" + reimb_status_id +
                ", reimb_type_id=" + reimb_type_id +
                '}';
    }
}




