import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  CartesianGrid,
  Legend,
} from "recharts";

const AirdropBarChart = ({ data }) => {
  if (!data || data.length === 0) {
    return <div className="text-gray-500">Airdrop verisi bulunamadı.</div>;
  }

  // Beklenen: [{ tokenSymbol: "ARB", count: 2 }, { tokenSymbol: "OP", count: 1 }]
  return (
    <div className="w-full h-96 bg-white rounded-lg shadow-md p-4 mt-10">
      <ResponsiveContainer>
        <BarChart data={data} margin={{ top: 10, right: 20, left: 0, bottom: 5 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="tokenSymbol" />
          <YAxis allowDecimals={false} />
          <Tooltip />
          <Legend />
          <Bar dataKey="value" fill="#82ca9d" name="Airdrop Sayısı" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default AirdropBarChart;
