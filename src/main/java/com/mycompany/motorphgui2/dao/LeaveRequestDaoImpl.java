/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorphgui2.dao;

import com.mycompany.motorphgui2.entity.LeaveRequest;
import com.mycompany.motorphgui2.util.SessionFactoryUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Optional;

public class LeaveRequestDaoImpl implements Dao<LeaveRequest, Integer> {

    @Override
    public Optional<LeaveRequest> get(Integer id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(LeaveRequest.class, id));
        }
    }

    @Override
    public List<LeaveRequest> getAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LeaveRequest", LeaveRequest.class).list();
        }
    }

    @Override
    public void save(LeaveRequest leaveRequest) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(leaveRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(LeaveRequest leaveRequest) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(leaveRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(LeaveRequest leaveRequest) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(leaveRequest);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}