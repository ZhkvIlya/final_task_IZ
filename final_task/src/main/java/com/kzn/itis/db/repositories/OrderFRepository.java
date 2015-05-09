package com.kzn.itis.db.repositories;

import com.kzn.itis.db.model.OrderF;

public interface OrderFRepository {
    OrderF addOrder(OrderF event);
    OrderF removeOrder(int id);
    void orderFUpdate(int id,OrderF event);
    OrderF getOrder(int id);
    long getCount();
}