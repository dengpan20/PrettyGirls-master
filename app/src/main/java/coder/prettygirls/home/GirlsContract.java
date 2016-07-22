package coder.prettygirls.home;

import java.util.List;

import coder.prettygirls.BasePresenter;
import coder.prettygirls.BaseView;
import coder.prettygirls.data.bean.FPicBean;
import coder.prettygirls.data.bean.GirlsBean;

/**
 * Created by oracleen on 2016/6/29.
 */
public interface GirlsContract {

    interface View extends BaseView {
        void refresh(List<GirlsBean.ResultsEntity> datas);

        void load(List<GirlsBean.ResultsEntity> datas);

        void showError();

        void showNormal();

        void refreshFpic(List<FPicBean> girlBeanlist);

        void loadFpic(List<FPicBean> girlBeanlist);
    }

    interface Presenter extends BasePresenter {
        void getGirls(int page, int size, boolean isRefresh);
        void getGrils(int page ,boolean isRefresh);
    }
}
