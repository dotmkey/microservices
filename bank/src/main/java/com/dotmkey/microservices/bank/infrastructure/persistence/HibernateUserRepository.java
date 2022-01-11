//package com.dotmkey.bank.infrastructure.persistence;
//
//import com.dotmkey.bank.com.dotmkey.microservices.auth.domain.model.identity.User;
//import com.dotmkey.bank.com.dotmkey.microservices.auth.domain.model.identity.UserRepository;
//import lombok.AllArgsConstructor;
//import org.hibernate.SessionFactory;
//
//@AllArgsConstructor
//public class HibernateUserRepository implements UserRepository {
//    private SessionFactory sessionFactory;
//
//    @Override
//    public void save(User user) {
//        var session = this.sessionFactory.openSession();
//
//        try (session) {
//            var transaction = session.beginTransaction();
//            session.save(user);
//            transaction.commit();
//        }
//    }
//}
