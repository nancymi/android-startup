package tw.helloandroid.data.server.requests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tw.helloandroid.data.server.ForecastResult;

import static tw.helloandroid.data.server.requests.DataRequest.COMPLETE_URL;

public interface ForecastApi {
    @GET("data/2.5/forecast/daily?mode=json&units=metric&cnt=7&APPID=15646a06818f61f7b8d7823ca833e1ce&zip={zipCode}")
    Call<ForecastResult> getForecastResult(@Path("zipCode") Long zipCode);
}
