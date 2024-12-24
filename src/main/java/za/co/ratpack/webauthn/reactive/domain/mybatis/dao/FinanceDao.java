package za.co.ratpack.webauthn.reactive.domain.mybatis.dao;

import za.co.ratpack.webauthn.reactive.domain.mybatis.model.Currencies;

public interface FinanceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Currencies record);

    int insertSelective(Currencies record);

    int updateByPrimaryKeySelective(Currencies record);

    int updateByPrimaryKey(Currencies record);
}