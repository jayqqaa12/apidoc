import {Injectable} from '@angular/core';
import {HttpClient,HttpHeaders,HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";

/**
 * @description 定义全局接口，封装http服务<br>
 *     封装angular原生HttpClient类
 * @date 2017-9-15
 * @author admin
 */
@Injectable()
export class HttpService {

  // 注入httpClient
  constructor(private httpClient: HttpClient) {
  }


  /**
   * get请求
   * @param {string} url
   * @return {Observable<Object>}
   */
  public get(url: string,  options?: {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
} ): Observable<Object> {

    return this.httpClient.get(url,options);
  }

  /**
   * post请求
   * @param {string} url
   * @param body
   * @return {Observable<Object>}
   */
  public post(url: string, body: any | null, options?: {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
}): Observable<Object> {
    return this.httpClient.post(url, body,options);
  }

  /**
   * put请求
   * @param {string} url
   * @param body
   * @return {Observable<Object>}
   */
  public put(url: string, body: any | null, options?: {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
} ): Observable<Object> {
    return this.httpClient.put(url, body,options);
  }

  /**
   * delete请求
   * @param {string} url
   * @return {Observable<Object>}
   */
  public delete(url: string, options?: {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    observe?: 'body';
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
}): Observable<Object> {
    return this.httpClient.delete(url,options);
  }


  /**
   * 文件上传
   * @param {string} url
   * @param $event
   * @param {string} fileKey
   * @param obj
   * @return {Observable<Object>}
   */
  upload(url: string, $event, fileKey: string, ...obj: any[]): Observable<Object> {
    const files = $event.target.files || $event.srcElement.files;
    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append(fileKey, files[i]);
    }
    if (obj) {
      // console.log(JSON.stringify(obj));
      for (let i = 0; i < obj.length; i++) {
        console.log(JSON.stringify(obj[i]));
        // 这里拼接对象
        // formData.append("obj", obj[i].value);
      }
    }
    // console.log("files", files)
    // console.log("formData", formData)
    return this.httpClient.post(url, formData);
  }

}

