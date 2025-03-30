import { useEffect, useState } from "react";
import { useAuthContext } from "../context/auth-context";
import { http } from "../util/http";
import { Transaction } from "../components/transaction";

export default function Transactions() {
  const authContext = useAuthContext();

  const [transactions, setTransaction] = useState([]);

  useEffect(() => {
    async function getTransactions() {
      const transactions = await http.get("/transactions");
      setTransaction(transactions);
    }
    getTransactions();
  }, []);

  return (
    <>
      <h2 className="pb-3 text-lg font-medium">
        Hi, {authContext.name}. Here are all your transactions.
      </h2>
      <table>
        <tbody>
          {transactions.map((t) => (
            <Transaction key={t.id} {...t} />
          ))}
        </tbody>
      </table>
    </>
  );
}
