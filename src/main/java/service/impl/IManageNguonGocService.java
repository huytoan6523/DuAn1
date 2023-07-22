/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.NguonGoc;
import java.util.List;

/**
 *
 * @author Uy Tin
 */
public interface IManageNguonGocService {

    List<NguonGoc> getAll();

    NguonGoc getOne(String ten);

    boolean add(NguonGoc ng);

    boolean update(NguonGoc ng);

    boolean delete(NguonGoc ng);
}
