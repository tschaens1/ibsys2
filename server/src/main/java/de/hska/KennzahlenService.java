package de.hska;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KennzahlenService {

    @Autowired
    private IXmlFileRepository xmlFileRepository;

    public void initialize(){
    }
}
