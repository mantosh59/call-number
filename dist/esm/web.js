import { WebPlugin } from "@capacitor/core";
export class CallNumber extends WebPlugin {
    constructor() {
        super({
            name: "CallNumber",
            platforms: ["web"],
        });
    }
    showFilePicker(_options) {
        return Promise.reject("Method not implemented");
    }
}
//# sourceMappingURL=web.js.map