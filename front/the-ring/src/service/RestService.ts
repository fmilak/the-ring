import RestInit from "../model/api/RestInit";
import ApiResponse from "../model/api/ApiResponse";

class RestService {

    public static async fetch(restInit: RestInit, callback: Function): Promise<any> {
        const url = `http://localhost:8080${restInit.url}`;
        const response: any = await fetch(url, {
            headers: restInit.header,
            body: restInit.body !== '' ? restInit.body : null,
            method: restInit.method
        });
        console.log(response);
        const responseJson: string = await response.json();
        console.log(responseJson);
        this.handleResponseJson(responseJson, callback);
    }

    private static handleResponseJson(responseJson: any, callback: Function): void {
        const apiResponse: ApiResponse = new ApiResponse();
        apiResponse.success = responseJson.success;
        apiResponse.data = JSON.parse(responseJson.data);
        callback(apiResponse);
    }

}

export default RestService;