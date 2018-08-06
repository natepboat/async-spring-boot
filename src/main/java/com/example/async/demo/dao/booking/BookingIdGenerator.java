package com.example.async.demo.dao.booking;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.CriteriaImpl;

import java.io.Serializable;

public class BookingIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object o) throws HibernateException {
        String prefix = "BK";
        CriteriaImpl criteria = new CriteriaImpl(o.getClass().getName(), session);
        criteria.setProjection(Projections.count("id"));
        long total = (long) criteria.uniqueResult();
        String suffix = String.format("%04d", total);
        return prefix.concat(suffix);
    }
}
