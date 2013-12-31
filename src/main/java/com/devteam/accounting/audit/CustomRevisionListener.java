package com.devteam.accounting.audit;

import com.devteam.accounting.persistence.audit.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity revision = (CustomRevisionEntity) revisionEntity;
        revision.setUsername("mr. foo");
    }

}