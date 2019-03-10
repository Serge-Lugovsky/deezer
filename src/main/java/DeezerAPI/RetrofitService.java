package DeezerAPI;

import okhttp3.OkHttpClient;
import org.apache.http.util.TextUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class RetrofitService {

    private static final String API_BASE_URL = "https://api.deezer.com/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static RetrofitService retrofitInstance;
    private Retrofit retrofit;

    private RetrofitService(){

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    private static RetrofitService getInstance(){
        if (retrofitInstance == null){
            retrofitInstance = new RetrofitService();
        }
        return retrofitInstance;
    }

    static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }
        return getInstance()
                .retrofit
                .create(serviceClass);
    }

}
