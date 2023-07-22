/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.DongGo;
import java.util.List;

/**
 *
 * @author Uy Tin
 */
public interface IManageDongGoService {

    List<DongGo> getAll();

    DongGo getOne(String ten);

    Boolean them(DongGo dongGo);

    Boolean sua(DongGo dongGo);

    Boolean xoa(DongGo dongGo);
}
