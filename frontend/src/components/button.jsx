export function Button(props) {
  return (
    <button
      className="w-full rounded bg-slate-700 p-2 font-medium text-white transition-all duration-50 focus:outline-3 focus:outline-slate-400"
      {...props}
    >
      {props.children}
    </button>
  );
}
