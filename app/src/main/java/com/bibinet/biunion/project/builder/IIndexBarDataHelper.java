package com.bibinet.biunion.project.builder;



import com.bibinet.biunion.project.bean.cityselectbean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by zp on 2016/12/6.
 */
public interface IIndexBarDataHelper {
    //汉语》》拼音
    IIndexBarDataHelper convert(List<? extends BaseIndexPinyinBean> datas);

    //拼音》》tag
    IIndexBarDataHelper fillInexTag(List<? extends BaseIndexPinyinBean> datas);

    //对数据源进行排序
    IIndexBarDataHelper sortSourceDatas(List<? extends BaseIndexPinyinBean> datas);

    //对index的数据排序 sortSourceDatas
    IIndexBarDataHelper getSortIndexDatas(List<? extends BaseIndexPinyinBean> datas, List<String> indexDatas);


}
