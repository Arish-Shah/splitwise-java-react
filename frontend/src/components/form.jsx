import { Button } from "./button";

export function FormInput(props) {
  return (
    <div className="mt-3">
      <label htmlFor={props.id} className="text-sm font-medium">
        {props.label}
      </label>
      <input
        className="mt-1 w-full rounded-md border-1 border-slate-300 px-2 py-1 outline-0 outline-slate-200 transition-all duration-50 focus:outline-3"
        {...props}
      />
    </div>
  );
}

export function FormButton({ children }) {
  return (
    <div className="mt-5">
      <Button type="submit">{children}</Button>
    </div>
  );
}
