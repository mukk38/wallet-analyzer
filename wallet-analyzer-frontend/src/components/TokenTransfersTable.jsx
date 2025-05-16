const TokenTransfersTable = ({ data }) => {
  if (!data || data.length === 0) {
    return <div className="text-gray-500">Token hareketi bulunamadı.</div>;
  }

  return (
    <div className="overflow-x-auto mt-8">
      <table className="min-w-full bg-white shadow-md rounded-lg">
        <thead>
          <tr className="bg-purple-600 text-white">
            <th className="py-3 px-4 text-left">Token</th>
            <th className="py-3 px-4 text-left">Sembol</th>
            <th className="py-3 px-4 text-left">Miktar</th>
            <th className="py-3 px-4 text-left">Kimden</th>
            <th className="py-3 px-4 text-left">Kime</th>
            <th className="py-3 px-4 text-left">Tarih</th>
          </tr>
        </thead>
        <tbody>
          {data.map((tx, index) => (
            <tr
              key={index}
              className={`border-t ${index % 2 === 0 ? "bg-gray-50" : "bg-white"}`}
            >
              <td className="py-2 px-4">{tx.tokenName}</td>
              <td className="py-2 px-4">{tx.tokenSymbol}</td>
              <td className="py-2 px-4">{parseFloat(tx.amount).toFixed(4)}</td>
              <td className="py-2 px-4">{tx.from}</td>
              <td className="py-2 px-4">{tx.to}</td>
              <td className="py-2 px-4">
                {new Date(parseInt(tx.timeStamp) * 1000).toLocaleString()}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TokenTransfersTable;
