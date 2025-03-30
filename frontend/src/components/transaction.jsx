import { useState } from "react";
import { useAuthContext } from "../context/auth-context";
import {toast} from "react-hot-toast";
import { http } from "../util/http";

export function Transaction(props) {
  const authContext = useAuthContext();
  const [paid, setPaid] = useState(props.status);

  const isMeRequester = props.requester.email === authContext.email;
  const isMePayer = props.payer.email === authContext.email;

  const handleClick = () => {
    setPaid("PAID");

    toast(() => http.put(`/transactions/${props.id}`), {
      loading: "Paying...",
      success: "Paid successfully",
    error: e => e.message,
});
  };

  const classNames =
    isMePayer && paid === "PENDING"
      ? "visible pointer-events-all"
      : "hidden pointer-events-none";

  return (
    <tr className="border-t border-t-slate-200">
      <td className="w-full p-2">
        <span className="font-medium">
          {isMeRequester ? "You" : props.requester.name}
        </span>{" "}
        requested from{" "}
        <span className="font-medium">
          {isMePayer ? "You" : props.payer.name}
        </span>
        <div>
          <span className="text-sm text-slate-500">
            {new Intl.DateTimeFormat("en-IN", {
              dateStyle: "medium",
              timeStyle: "short",
            }).format(new Date(props.createdAt))}
          </span>
        </div>
        <div className="text-xs text-slate-400">For {props.description}</div>
      </td>
      <td className="p-2 px-6 text-lg font-medium">₹{props.amount}</td>
      <td className="h-full p-2">
        {isMePayer && paid === "PENDING" && (
          <button
            onClick={handleClick}
            className={
              classNames +
              " rounded bg-slate-700 px-2 py-1 text-xs font-medium text-white transition-all duration-50 focus:outline-3 focus:outline-slate-400"
            }
          >
            {paid}
          </button>
        )}
      </td>
    </tr>
  );
}
