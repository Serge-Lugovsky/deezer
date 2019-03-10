package DeezerAPI;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    private String accessToken;

    AuthenticationInterceptor(String token) {
        this.accessToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("access_token", accessToken)
                .build();

        Request.Builder builder = original.newBuilder()
              .url(url);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
