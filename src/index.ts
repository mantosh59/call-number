import { registerPlugin } from "@capacitor/core";
import { CallNumberPlugin } from "./definitions";

const CallNumber = registerPlugin<CallNumberPlugin>("CallNumber", {
  web: () => import("./web").then((m) => m.CallNumber),
});

export * from "./definitions";
export { CallNumber };
