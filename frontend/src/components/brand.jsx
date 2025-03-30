import { Link } from "react-router";

export function Brand({ size }) {
  const classNames = size === "lg" ? "text-center text-2xl": "text-xl";

  return <h1 className={classNames + " font-medium"}>
    <Link to="/">Splitwise</Link>
  </h1>;
}
