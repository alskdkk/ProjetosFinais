export default class DataLogger {
  static log(event, data) {
    console.log(`[LOG - ${event}]`, data);
  }
}