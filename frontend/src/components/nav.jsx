import { NavLink } from "react-router";
import { useAuthContext } from "../context/auth-context";
import { Brand } from "./brand";

export function Nav() {
  const authContext = useAuthContext();

  return (
    <header className="flex justify-between p-3">
      <nav>
        <Brand size="sm" />
      </nav>
      <ul className="flex items-center gap-4 font-medium">
        <li className="">
          <NavLink to="/transactions" className="hover:underline">
            Transactions
          </NavLink>
        </li>
        <li className="">
          <button
            onClick={authContext.signout}
            className="cursor-pointer text-red-700"
          >
            Sign out
          </button>
        </li>
      </ul>
    </header>
  );
}
