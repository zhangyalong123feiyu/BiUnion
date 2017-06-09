package com.bibinet.biunion.project.builder;




import com.bibinet.biunion.project.bean.cityselectbean.BaseIndexPinyinBean;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zp on 2016/12/7.
 */
public class IndexBarDataHelperImpl implements IIndexBarDataHelper {

    @Override
    public IIndexBarDataHelper convert(List<? extends BaseIndexPinyinBean> datas) {
        if (null == datas || datas.isEmpty()) {
            return this;
        }
        for (int i = 0; i < datas.size(); i++) {
            BaseIndexPinyinBean pinyinBean = datas.get(i);
            if (pinyinBean.isNeedToPinyin()) {
                StringBuilder sb = new StringBuilder();
                String target = pinyinBean.getTarget();//取出需要被转化成拼音的字段
                for (int j = 0; j < target.length(); j++) {
                    sb.append(Pinyin.toPinyin(target.charAt(j)).toUpperCase());
                }
                pinyinBean.setCityPinyin(sb.toString());//设置城市全拼

            }


        }
        return this;
    }

    @Override
    public IIndexBarDataHelper fillInexTag(List<? extends BaseIndexPinyinBean> datas) {
        if (null == datas || datas.isEmpty()) {
            return this;
        }
        int size = datas.size();
        for (int i = 0; i < size; i++) {
            BaseIndexPinyinBean baseIndexPinyinBean = datas.get(i);
            if (baseIndexPinyinBean.isNeedToPinyin()) {
                String pinyin = baseIndexPinyinBean.getCityPinyin();
                String tag = pinyin.substring(0, 1);
                if (tag.matches("[A-Z]")) {
                    baseIndexPinyinBean.setIndexTag(tag);
                } else {
                    baseIndexPinyinBean.setIndexTag("#");
                }

            }
        }

        return this;
    }

    @Override
    public IIndexBarDataHelper sortSourceDatas(List<? extends BaseIndexPinyinBean> datas) {
        if (null == datas || datas.isEmpty()) {
            return this;
        }
        convert(datas);
        fillInexTag(datas);

        Collections.sort(datas, new Comparator<BaseIndexPinyinBean>() {
            @Override
            public int compare(BaseIndexPinyinBean lhs, BaseIndexPinyinBean rhs) {
                if(!lhs.isNeedToPinyin()){
                    return 0;
                }else if(!rhs.isNeedToPinyin()){
                    return 0;
                }else if(lhs.getIndexTag().equals("#")){
                    return 1;
                }else if(rhs.getIndexTag().equals("#")){
                    return -1;
                }else {
                    return lhs.getCityPinyin().compareTo(rhs.getCityPinyin());
                }
            }
        });

        return this;
    }

    @Override
    public IIndexBarDataHelper getSortIndexDatas(List<? extends BaseIndexPinyinBean> datas, List<String> indexDatas) {
        if(null==datas||datas.isEmpty()){
            return this;
        }
        //按数据源来 此时sourceDatas 已经有序
        int size=datas.size();
        for(int i=0;i<size;i++){
            String tag=datas.get(i).getIndexTag();
            if(!indexDatas.contains(tag)){//则判断是否已经将这个索引添加进去，若没有则添加
                indexDatas.add(tag);
            }
        }
        return this;
    }


}
