import Capacitor 

@objc(CallNumber)
public class CallNumber: CAPPlugin {

  @objc func call(_ call: CAPPluginCall) {
    let phoneNumber = call.getString("number") ?? ""
    guard let number = URL(string: "tel://" + phoneNumber) else { return }
        UIApplication.shared.open(number)
    call.resolve()
  }
  
 }
