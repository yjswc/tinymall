package com.cskaoyan.service;

import com.cskaoyan.bean.system.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 11:12
 * @Version: 1.0
 */
public interface StorageService {
    String createStorage(MultipartFile file) throws IOException;

    List<Storage> queryStorageList(String key, String name, Integer page, Integer limit, String sort, String order);

    Integer updateStorage(Storage storage);

    Integer deleteStorage(Storage storage);
}
