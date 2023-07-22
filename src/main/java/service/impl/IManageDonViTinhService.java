/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.impl;

import domainModels.DonViTinh;
import java.util.List;

/**
 *
 * @author Uy Tin
 */
public interface IManageDonViTinhService {

    List<DonViTinh> getAll();

    DonViTinh getOne(String ten);

    boolean add(DonViTinh dvt);

    boolean update(DonViTinh dvt);

    boolean delete(DonViTinh dvt);
}
