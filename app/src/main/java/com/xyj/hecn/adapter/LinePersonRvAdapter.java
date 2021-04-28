package com.xyj.hecn.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.xyj.hecn.R;
import com.xyj.hecn.bean.MqttMessageBean;
import com.xyj.hecn.bean.PatientBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * LinePersonRvAdapter
 * description: 适配器
 *
 * @author : Licy
 * @date : 2021/4/25
 * email ：licy3051@qq.com
 */
public class LinePersonRvAdapter extends BaseQuickAdapter<MqttMessageBean.DataBean, BaseViewHolder> {

    public LinePersonRvAdapter(@Nullable List<MqttMessageBean.DataBean> data) {
        super(R.layout.adapter_rv_line_person, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MqttMessageBean.DataBean patientBean) {
        baseViewHolder.setText(R.id.tv_name, patientBean.getName())
                .setText(R.id.tv_number, patientBean.getRecordId());
    }
}
