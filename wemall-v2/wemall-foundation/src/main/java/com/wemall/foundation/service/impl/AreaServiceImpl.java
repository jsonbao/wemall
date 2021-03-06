package com.wemall.foundation.service.impl;

import com.wemall.core.dao.IGenericDAO;
import com.wemall.core.query.GenericPageList;
import com.wemall.core.query.PageObject;
import com.wemall.core.query.support.IPageList;
import com.wemall.core.query.support.IQueryObject;
import com.wemall.foundation.domain.Area;
import com.wemall.foundation.service.IAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AreaServiceImpl
    implements IAreaService {
    @Resource(name = "areaDAO")
    private IGenericDAO<Area> areaDao;

    public boolean save(Area area){
        try {
            this.areaDao.save(area);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Area getObjById(Long id){
        Area area = (Area)this.areaDao.get(id);
        if (area != null){
            return area;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.areaDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> areaIds){
        for (Serializable id : areaIds){
            delete((Long)id);
        }

        return true;
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Area.class, query,
                params, this.areaDao);
        if (properties != null){
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
                             .getCurrentPage().intValue(), pageObj.getPageSize() == null ? 0 :
                             pageObj.getPageSize().intValue());
        }else{
            pList.doList(0, -1);
        }

        return pList;
    }

    public boolean update(Area area){
        try {
            this.areaDao.update(area);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Area> query(String query, Map params, int begin, int max){
        return this.areaDao.query(query, params, begin, max);
    }
}




