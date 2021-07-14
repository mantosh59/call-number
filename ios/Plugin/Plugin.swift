import Capacitor 

@objc(CallNumber)
public class call: CAPPlugin {
  @objc func echo(_ call: CAPPluginCall) {
    let number = call.getString("number") ?? ""
    callNumber(number: <#T##String#>)
    call.resolve()
  }
}

private func callNumber(phoneNumber: String){
    if let phoneURL = NSURL(string: ("tel://" + phoneNumber)) {
           let alert = UIAlertController(title: ("Call " + phoneNumber + "?"), message: nil, preferredStyle: .alert)
           alert.addAction(UIAlertAction(title: "Call", style: .default, handler: { (action) in
               UIApplication.shared.open(phoneURL as URL, options: [:], completionHandler: nil)
           }))

           alert.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
           self.present(alert, animated: true, completion: nil)
    }
}

// private func callNumber(phoneNumber: String) {
//     guard let url = URL(string: "telprompt://\(phoneNumber)"),
//         UIApplication.shared.canOpenURL(url) else {
//         return
//     }
//     UIApplication.shared.open(url, options: [:], completionHandler: nil)
// }
