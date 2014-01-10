package com.devteam.accounting.audit;

import com.devteam.accounting.persistence.audit.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.plugin.liveconnect.SecurityContextHelper;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomRevisionEntity revision = (CustomRevisionEntity) revisionEntity;
        revision.setUsername("mr. foo");
    }

}