
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>   // 使用WiFiMulti库 
#include <ESP8266WebServer.h>   // 使用WebServer库
 
ESP8266WiFiMulti wifiMulti;     // 建立ESP8266WiFiMulti对象,对象名称是 'wifiMulti'
 
ESP8266WebServer server(80);    // 建立网络服务器对象，该对象用于响应HTTP请求。监听端口（80）
 
IPAddress local_IP(192, 168, 43, 123); // 设置ESP8266-NodeMCU联网后的IP
IPAddress gateway(192, 168, 43, 1);    // 设置网关IP
IPAddress subnet(255, 255, 255, 0);   // 设置子网掩码
IPAddress dns(192,168,43,1); 
// 设置局域网DNS的IP
 
void setup(void){
  Serial.begin(9600);          // 启动串口通讯
  Serial.println("");
 
  pinMode(LED_BUILTIN, OUTPUT);   
  digitalWrite(LED_BUILTIN, HIGH);
  
  // 设置开发板网络环境
  if (!WiFi.config(local_IP, gateway, subnet)) {
    Serial.println("Failed to Config ESP8266 IP"); 
  } 
  
  wifiMulti.addAP("ESP8266", "12345678"); // 将需要连接的一系列WiFi ID和密码输入这里
  Serial.println("Connecting ...");                            
  
  // 尝试进行wifi连接。
  while (wifiMulti.run() != WL_CONNECTED) { 
    delay(250);
    Serial.print('.');
  }
 
  // WiFi连接成功后将通过串口监视器输出连接成功信息 
  Serial.println('\n');
  Serial.print("Connected to ");
  Serial.println(WiFi.SSID());              // 通过串口监视器输出连接的WiFi名称
  Serial.print("IP address:\t");
  Serial.println(WiFi.localIP());           // 通过串口监视器输出ESP8266-NodeMCU的IP
 
  server.on("/update", handleUpdate);        // 处理服务器更新函数
  server.on("/setLED",handlePWM);             // 处理PWM设置请求
  server.on("/breath",Breath);             // 处理breath请求
  server.onNotFound(handleUserRequest);       // 处理网络请求

  
  server.begin();                            // 启动网站服务
  Serial.println("HTTP server started");
}
 
void loop(void){
  server.handleClient();                    // 检查http服务器访问
}
 
void handleUpdate(){
  Serial.println("已连接");
  int buttonValue = server.arg("button").toInt();  // 获取客户端发送HTTP信息中的按键控制量
  
  server.send(200, "text/plain", "Received");                 // 发送http响应
 
  buttonValue == 0 ? digitalWrite(LED_BUILTIN, LOW) : digitalWrite(LED_BUILTIN, HIGH);

  // 通过串口监视器输出获取到的变量数值
  Serial.print("buttonValue = ");  Serial.println(buttonValue);   
  Serial.println("=================");    
}
void handlePWM(){
  String pwmStr = server.arg("pwm"); // 获取用户请求中的PWM数值
  Serial.print("pwmStr = ");Serial.println(pwmStr);
  
  int pwmVal = pwmStr.toInt();              // 将用户请求中的PWM数值转换为整数
  pwmVal = 1023 - map(pwmVal,0,100,0,1023); // 用户请求数值为0-100，转为0-1023
  Serial.print("pwmVal = ");Serial.println(pwmVal);
  
  analogWrite(LED_BUILTIN, pwmVal);         // 实现PWM引脚设置
  server.send(200, "text/plain");//向客户端发送200响应信息
}

// 处理用户浏览器的HTTP访问
void handleUserRequest() {                                                     
  server.send(404, "text/plain", "404 Not Found"); 
  
}
void Breath(){
  server.send(200, "text/plain", "Received");  
  while(true){
    for (int brightness = 0; brightness <= 1023; brightness+=10){
    analogWrite(LED_BUILTIN, brightness);   
    Serial.println(brightness);
    delay(10); 
  }
  
  // LED由明到暗  
  for (int brightness = 1023; brightness >=0 ; brightness-=10){
    analogWrite(LED_BUILTIN, brightness);
    Serial.println(brightness);
    delay(10);
   
  }
 }
}
