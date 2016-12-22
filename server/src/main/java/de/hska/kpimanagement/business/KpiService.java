package de.hska.kpimanagement.business;

import de.hska.filemanagement.business.IJsonFileRepository;
import de.hska.filemanagement.domain.JsonFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KpiService {

    @Autowired
    private IJsonFileRepository xmlFileRepository;

    public void initialize(JsonFile jsonFile){
    }
}
