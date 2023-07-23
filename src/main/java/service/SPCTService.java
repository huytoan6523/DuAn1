/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domainModels.SPCT;
import java.util.List;
import repositories.SPCTRepository;
import service.impl.ISPCTService;

/**
 *
 * @author tungs
 */
public class SPCTService implements ISPCTService {

    private SPCTRepository repo;

    public SPCTService() {
        repo = new SPCTRepository();
    }

    @Override
    public List<SPCT> getAll() {
        return repo.getAll();
    }

    @Override
    public SPCT getOne(String ten) {
        return repo.getOne(ten);
    }

    @Override
    public String addOrSave(SPCT x) {
        if (!repo.addOrSave(x)) {
            return "thất bại";
        }
        return "thành công";
    }

    @Override
    public String delete(SPCT x) {
        if (!repo.delete(x)) {
            return "thất bại";
        }
        return "thành công";
    }
}
