package edu.tjrac.swant.qqmusic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wpc on 2018/4/27.
 */

public class PackageSearchResult implements Parcelable{


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"msg":"成功","code":"0000","s":{"msg":"查询成功,扣费","result":{"msg":"ok","result":{"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]},"status":"0"},"code":"10000","charge":true},"data":{"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]}}
     */

    private String code;
    private boolean charge;
    private String msg;
    private ResultBeanXX result;

    protected PackageSearchResult(Parcel in) {
        code = in.readString();
        charge = in.readByte() != 0;
        msg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeByte((byte) (charge ? 1 : 0));
        dest.writeString(msg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PackageSearchResult> CREATOR = new Creator<PackageSearchResult>() {
        @Override
        public PackageSearchResult createFromParcel(Parcel in) {
            return new PackageSearchResult(in);
        }

        @Override
        public PackageSearchResult[] newArray(int size) {
            return new PackageSearchResult[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBeanXX getResult() {
        return result;
    }

    public void setResult(ResultBeanXX result) {
        this.result = result;
    }

    public static class ResultBeanXX implements Parcelable{
        /**
         * msg : 成功
         * code : 0000
         * s : {"msg":"查询成功,扣费","result":{"msg":"ok","result":{"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]},"status":"0"},"code":"10000","charge":true}
         * data : {"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]}
         */

        private String msg;
        private String code;
        private SBean s;
        private DataBean data;

        protected ResultBeanXX(Parcel in) {
            msg = in.readString();
            code = in.readString();
        }

        public static final Creator<ResultBeanXX> CREATOR = new Creator<ResultBeanXX>() {
            @Override
            public ResultBeanXX createFromParcel(Parcel in) {
                return new ResultBeanXX(in);
            }

            @Override
            public ResultBeanXX[] newArray(int size) {
                return new ResultBeanXX[size];
            }
        };

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public SBean getS() {
            return s;
        }

        public void setS(SBean s) {
            this.s = s;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(msg);
            dest.writeString(code);
        }

        public static class SBean {
            /**
             * msg : 查询成功,扣费
             * result : {"msg":"ok","result":{"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]},"status":"0"}
             * code : 10000
             * charge : true
             */

            private String msg;
            private ResultBeanX result;
            private String code;
            private boolean charge;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public ResultBeanX getResult() {
                return result;
            }

            public void setResult(ResultBeanX result) {
                this.result = result;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public boolean isCharge() {
                return charge;
            }

            public void setCharge(boolean charge) {
                this.charge = charge;
            }

            public static class ResultBeanX {
                /**
                 * msg : ok
                 * result : {"issign":"1","number":"454244690951","expName":"中通快递","deliverystatus":"3","expSite":"www.zto.com","expPhone":"95311","type":"zto","list":[{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]}
                 * status : 0
                 */

                private String msg;
                private ResultBean result;
                private String status;

                public String getMsg() {
                    return msg;
                }

                public void setMsg(String msg) {
                    this.msg = msg;
                }

                public ResultBean getResult() {
                    return result;
                }

                public void setResult(ResultBean result) {
                    this.result = result;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public static class ResultBean {
                    /**
                     * issign : 1
                     * number : 454244690951
                     * expName : 中通快递
                     * deliverystatus : 3
                     * expSite : www.zto.com
                     * expPhone : 95311
                     * type : zto
                     * list : [{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]
                     */

                    private String issign;
                    private String number;
                    private String expName;
                    private String deliverystatus;
                    private String expSite;
                    private String expPhone;
                    private String type;
                    private List<ListBean> list;

                    public String getIssign() {
                        return issign;
                    }

                    public void setIssign(String issign) {
                        this.issign = issign;
                    }

                    public String getNumber() {
                        return number;
                    }

                    public void setNumber(String number) {
                        this.number = number;
                    }

                    public String getExpName() {
                        return expName;
                    }

                    public void setExpName(String expName) {
                        this.expName = expName;
                    }

                    public String getDeliverystatus() {
                        return deliverystatus;
                    }

                    public void setDeliverystatus(String deliverystatus) {
                        this.deliverystatus = deliverystatus;
                    }

                    public String getExpSite() {
                        return expSite;
                    }

                    public void setExpSite(String expSite) {
                        this.expSite = expSite;
                    }

                    public String getExpPhone() {
                        return expPhone;
                    }

                    public void setExpPhone(String expPhone) {
                        this.expPhone = expPhone;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public List<ListBean> getList() {
                        return list;
                    }

                    public void setList(List<ListBean> list) {
                        this.list = list;
                    }

                    public static class ListBean {
                        /**
                         * time : 2017-09-19 18:01:22
                         * status : [成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!
                         */

                        private String time;
                        private String status;

                        public String getTime() {
                            return time;
                        }

                        public void setTime(String time) {
                            this.time = time;
                        }

                        public String getStatus() {
                            return status;
                        }

                        public void setStatus(String status) {
                            this.status = status;
                        }
                    }
                }
            }
        }

        public static class DataBean implements Parcelable{
            /**
             * issign : 1
             * number : 454244690951
             * expName : 中通快递
             * deliverystatus : 3
             * expSite : www.zto.com
             * expPhone : 95311
             * type : zto
             * list : [{"time":"2017-09-19 18:01:22","status":"[成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-09-19 14:13:45","status":"[成都市]  快件已到达 [成都华阳],业务员 雍-何淋(18190713228) 正在第2次派件, 请保持电话畅通,并耐心等待"},{"time":"2017-09-19 04:30:04","status":"[成都市]  快件离开 [成都中转] 发往 [成都华阳]"},{"time":"2017-09-18 18:28:08","status":"[南充市]  快件到达 [南充中转站]"},{"time":"2017-09-18 03:42:33","status":"[荆州市]  快件到达 [荆州中转部]"},{"time":"2017-09-17 21:41:00","status":"[武汉市]  快件离开 [武汉中转部] 发往 [成都中转]"},{"time":"2017-09-17 19:56:01","status":"[武汉市]  快件到达 [武汉中转部]"},{"time":"2017-09-17 13:52:50","status":"[武汉市]  [中吉武汉仓] 的 鑫源泽数码专营店 (17742032635) 已收件"}]
             */

            private String issign;
            private String number;
            private String expName;
            private String deliverystatus;
            private String expSite;
            private String expPhone;
            private String type;
            private List<ListBeanX> list;

            protected DataBean(Parcel in) {
                issign = in.readString();
                number = in.readString();
                expName = in.readString();
                deliverystatus = in.readString();
                expSite = in.readString();
                expPhone = in.readString();
                type = in.readString();
            }

            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel in) {
                    return new DataBean(in);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };

            public String getIssign() {
                return issign;
            }

            public void setIssign(String issign) {
                this.issign = issign;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getExpName() {
                return expName;
            }

            public void setExpName(String expName) {
                this.expName = expName;
            }

            public String getDeliverystatus() {
                return deliverystatus;
            }

            public void setDeliverystatus(String deliverystatus) {
                this.deliverystatus = deliverystatus;
            }

            public String getExpSite() {
                return expSite;
            }

            public void setExpSite(String expSite) {
                this.expSite = expSite;
            }

            public String getExpPhone() {
                return expPhone;
            }

            public void setExpPhone(String expPhone) {
                this.expPhone = expPhone;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(issign);
                dest.writeString(number);
                dest.writeString(expName);
                dest.writeString(deliverystatus);
                dest.writeString(expSite);
                dest.writeString(expPhone);
                dest.writeString(type);
            }

            public static class ListBeanX implements Parcelable {
                /**
                 * time : 2017-09-19 18:01:22
                 * status : [成都市]  快件已在 [成都华阳] 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!
                 */

                private String time;
                private String status;

                protected ListBeanX(Parcel in) {
                    time = in.readString();
                    status = in.readString();
                }

                public static final Creator<ListBeanX> CREATOR = new Creator<ListBeanX>() {
                    @Override
                    public ListBeanX createFromParcel(Parcel in) {
                        return new ListBeanX(in);
                    }

                    @Override
                    public ListBeanX[] newArray(int size) {
                        return new ListBeanX[size];
                    }
                };

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(time);
                    dest.writeString(status);
                }
            }
        }
    }
}
