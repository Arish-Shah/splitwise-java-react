import { NavLink } from "react-router";
import { useAuthContext } from "../context/auth-context";
import { Brand } from "./brand";

export function Nav() {
  const authContext = useAuthContext();

  return <header className="flex justify-between">
    <nav className="p-3">
      <Brand size="sm" />
    </nav>
    <ul className="flex gap-4 items-center font-medium">
      <li className="">
        <NavLink to="/transactions" className="hover:underline">Transactions</NavLink></li>
      <li className="">
        <button onClick={authContext.signout} className="text-red-700 cursor-pointer">Sign out</button>
      </li>
    </ul>
  </header>
}
