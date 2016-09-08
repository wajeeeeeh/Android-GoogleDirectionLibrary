/*

Copyright 2015 Akexorcist

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package beep_beep.ca.beep_beep.GoogleMaps.network;

import beep_beep.ca.beep_beep.GoogleMaps.constant.DirectionUrl;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akexorcist on 11/29/15 AD.
 */
public class DirectionAndPlaceConnection {
    private static DirectionAndPlaceConnection connection;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static DirectionAndPlaceConnection getInstance() {
        if (connection == null) {
            connection = new DirectionAndPlaceConnection();
        }
        return connection;
    }

    private DirectionAndPlaceService service;

    private static Retrofit.Builder builder = new Retrofit
            .Builder()
            .baseUrl(DirectionUrl.MAPS_API_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public DirectionAndPlaceService createService() {
        if (service == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            httpClient.addInterceptor(logging);
            Retrofit retrofit = builder.client(httpClient.build()).build();
            service = retrofit.create(DirectionAndPlaceService.class);
        }
        return service;
    }
}
