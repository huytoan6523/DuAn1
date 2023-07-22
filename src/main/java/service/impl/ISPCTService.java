/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.SPCT;
import java.util.List;

/**
 *
 * @author tungs
 */
public interface ISPCTService {

    List<SPCT> getAll();

    SPCT getOne(String ten);

    String addOrSave(SPCT x);

    String delete(SPCT x);

}
