package com.springboot.jdbc.h2.tasks.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class InvoiceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class InvoiceRowMapper implements RowMapper<Invoice> {
        @Override
        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Invoice Invoice = new Invoice();
            Invoice.setId(rs.getLong("id"));
            Invoice.setName(rs.getString("name"));
            Invoice.setInvoiceNumber(rs.getString("invoice_number"));
            return Invoice;
        }

    }

    public List<Invoice> findAll() {
        return jdbcTemplate.query("select * from Invoice", new InvoiceRepository.InvoiceRowMapper());
    }

    public Invoice findById(long id) {
        return jdbcTemplate.queryForObject("select * from Invoice where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Invoice>(Invoice.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from Invoice where id=?", new Object[] { id });
    }

    public int insert(Invoice Invoice) {
        return jdbcTemplate.update("insert into Invoice (id, name, invoice_number) " + "values(?,  ?, ?)",
                new Object[] { Invoice.getId(), Invoice.getName(), Invoice.getInvoiceNumber() });
    }

    public int update(Invoice Invoice) {
        return jdbcTemplate.update("update Invoice " + " set name = ?, invoice_number = ? " + " where id = ?",
                new Object[] { Invoice.getName(), Invoice.getInvoiceNumber(), Invoice.getId() });
    }

}

