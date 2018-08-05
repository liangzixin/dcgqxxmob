package com.xiangmu.lzx.Service;




import com.xiangmu.lzx.Bean.BaseResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FileUploadService {

    /**
     * 单文件上传
     * @param description
     * @param file @Part MultipartBody.Part file 我们使用MultipartBody.Part类，使我们能够发送实际文件 file就是要往服务器上传的文件
     * @return 状态信息
     */
    @Multipart
    @POST("UploadServer")
    Call<BaseResponse<String>> upload(@Part("description") RequestBody description,
                                      @Part MultipartBody.Part file);

    @Multipart
    @POST("product!saveproductinfomob.action")
    Call<BaseResponse<Integer>> uploadlzx(@PartMap Map<String, RequestBody> data,
                                         @Part() List<MultipartBody.Part> parts);

    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("UploadServer")
    Call<BaseResponse<String>> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts);


    /**
     * 通过 MultipartBody和@body作为参数来实现多文件上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST("product!saveproductinfomob.action")
    Call<BaseResponse<String>> uploadFileWithRequestBody(@Body MultipartBody multipartBody);
    /**
     * 通过 MultipartBody和@body作为参数来实现多文件上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @Multipart
    @POST("product!saveproductinfomob.action")
    Call<BaseResponse<String>>  uploadFileWithRequestBody1(@PartMap Map<String, RequestBody> map, @Body List<MultipartBody.Part> multipartBody);

}